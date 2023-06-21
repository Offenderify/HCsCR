/*
 * Copyright (c) 2023 Offenderify
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.femboypve.hcscr;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

/**
 * ModMenu config screen class.
 *
 * @author Offenderify
 */
public class HCsCRScreen extends Screen {
    private final Screen parent;
    private Checkbox enabled;

    public HCsCRScreen(Screen parent) {
        super(Component.translatable("hcscr.screen.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        addRenderableWidget(enabled = new Checkbox(width / 2 - font.width(Component.translatable("hcscr.screen.enabled")), 40, 24 + font.width(Component.translatable("hcscr.screen.enabled")), 20, Component.translatable("hcscr.screen.enabled"), HCsCRFabric.enabled));
        addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> minecraft.setScreen(parent)).bounds(width / 2 - 75, height - 24, 150, 20).build());
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, delta);
        drawCenteredString(stack, font, title, width / 2, 10, -1);
        if (HCsCRFabric.disabledByCurrentServer) {
            drawCenteredString(stack, font, Component.translatable("hcscr.screen.serverDisabled"), width / 2, 70, 0xFFFF0000);
        }
    }

    @Override
    public void removed() {
        super.removed();
        HCsCRFabric.enabled = enabled.selected();
        HCsCRFabric.saveConfig();
    }
}
